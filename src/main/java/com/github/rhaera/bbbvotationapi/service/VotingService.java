package com.github.rhaera.bbbvotationapi.service;

import com.github.rhaera.bbbvotationapi.model.Participator;
import com.github.rhaera.bbbvotationapi.model.Vote;
import com.github.rhaera.bbbvotationapi.repository.VotingRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class VotingService {
    private static final String VOTING_TOPIC = "voting";
    private static final String GROUP_ID = "ApiVotes";
    private final KafkaTemplate<Object, Object> template;
    private final VotingRepository repository;

    public void sendEvent(Participator participator) {
        template.send(VOTING_TOPIC, participator);
    }
    @KafkaListener(topics = VOTING_TOPIC, groupId = GROUP_ID)
    private void execute(ConsumerRecord<String, Participator> register) throws IllegalArgumentException {
        String name;
        try {
            name = register.value()
                            .getName();
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(exception.getMessage());
        }
        repository.save(new Vote(UUID.nameUUIDFromBytes(name.substring(0, Math.min(10, name.length()))
                                                            .getBytes(StandardCharsets.UTF_8))
                                                            .toString()
                                                            .concat(UUID.randomUUID()
                                                            .toString()
                                                            .split("-")[0]),
                                register.value(),
                                Instant.now()));
    }
}
