package br.com.lacostech.pegasusbackend.services;

import br.com.lacostech.pegasusbackend.model.ThemeModel;
import br.com.lacostech.pegasusbackend.repositories.ThemeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ThemeService {

    private final ThemeRepository themeRepository;

    @Transactional(readOnly = true)
    public List<ThemeModel> findAll() {
        return themeRepository.findAll()
                .stream()
                .map(ThemeModel::new)
                .collect(Collectors.toList());
    }

}
