let stompClient;
let username;

document.addEventListener('DOMContentLoaded', function () {
    showUsernameInput();
});

function showUsernameInput() {
    document.getElementById('username-container').style.display = 'block';
    document.getElementById('chat-container').style.display = 'none';
}

function joinChat() {
    username = document.getElementById('username-input').value.trim();

    if (username !== '') {
        document.getElementById('username').innerText = username;
        document.getElementById('username-container').style.display = 'none';
        document.getElementById('chat-container').style.display = 'block';

        initializeWebSocket();
    } else {
        alert('Please enter a valid username.');
    }
}

function leaveChat() {
    document.getElementById('chat-container').style.display = 'none';
    document.getElementById('username-container').style.display = 'block';
    stompClient.disconnect();
}

function initializeWebSocket() {
    const socket = new SockJS('http://localhost:8080/websocket-endpoint');
    stompClient = Stomp.over(socket);

    stompClient.connect({}, function () {
        console.log('Connected to WebSocket');
    });

    stompClient.subscribe('/topic/group/group1', function (message) {
        const newMessage = JSON.parse(message.body);
        displayMessage(newMessage.username, newMessage.content);
    });
}

function sendMessage() {
    const content = document.getElementById('message-input').value.trim();

    if (content !== '') {
        const chatMessage = {
            groupId: 'group1', // Replace with the actual group ID
            username: username,
            content: content
        };

        stompClient.send('/app/sendMessage', {}, JSON.stringify(chatMessage));
        document.getElementById('message-input').value = '';
    }
}

function displayMessage(username, content) {
    const chatMessages = document.getElementById('chat-messages');
    const messageDiv = document.createElement('div');
    messageDiv.innerHTML = `<strong>${username}:</strong> ${content}`;
    chatMessages.appendChild(messageDiv);
    chatMessages.scrollTop = chatMessages.scrollHeight;
}
