package app.repository;

import app.model.Challenge;

public interface ChallengeRepository extends CRUDRepository<Challenge, Integer> {
    Challenge getByName(String name);
}
