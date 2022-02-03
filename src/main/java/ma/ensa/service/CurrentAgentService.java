package ma.ensa.service;

import ma.ensa.exception.DuplicatedException;
import ma.ensa.exception.NotFoundException;
import ma.ensa.model.CurrentAgent;

public interface CurrentAgentService {
    CurrentAgent save(CurrentAgent currentAgent) throws DuplicatedException;
    CurrentAgent update(CurrentAgent currentAgent) throws NotFoundException;
}
