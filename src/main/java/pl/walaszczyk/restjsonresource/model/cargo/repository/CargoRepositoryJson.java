package pl.walaszczyk.restjsonresource.model.cargo.repository;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;
import pl.walaszczyk.restjsonresource.model.cargo.Cargo;
import pl.walaszczyk.restjsonresource.rest.exceptions.GetDataFromJsonException;
import pl.walaszczyk.restjsonresource.utils.JsonToObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@Log4j2
public class CargoRepositoryJson implements CargoRepository {

    @Override
    public Optional<Cargo> findByFlightId(Integer flightId) {
        try {
            List<Cargo> cargos = JsonToObjectMapper.getObjectsFromJsonFile("src/main/resources/data/cargo.json", Cargo.class);
            return cargos.stream()
                    .filter(cargo -> Objects.equals(flightId, cargo.getFlightId()))
                    .findAny();
        } catch (IOException e) {
            log.error("CargoRepositoryJson - there is no json file in pointed localization", e);
            throw new GetDataFromJsonException(e.getMessage());
        }
    }

    @Override
    public List<Cargo> findAllByFlightIdIn(List<Integer> flightIds) {
        try {
            List<Cargo> cargos = JsonToObjectMapper.getObjectsFromJsonFile("src/main/resources/data/cargo.json", Cargo.class);
            return cargos.stream()
                    .filter(cargo -> flightIds.contains(cargo.getFlightId()))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            log.error("CargoRepositoryJson - there is no json file in pointed localization", e);
            throw new GetDataFromJsonException(e.getMessage());
        }
    }
}
