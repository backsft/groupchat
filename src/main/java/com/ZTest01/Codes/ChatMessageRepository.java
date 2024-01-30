package com.ZTest01.Codes;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {
	// Add custom query methods if needed
}
