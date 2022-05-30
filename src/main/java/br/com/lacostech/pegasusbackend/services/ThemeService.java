package br.com.lacostech.pegasusbackend.services;

import br.com.lacostech.pegasusbackend.model.ThemeModel;
import br.com.lacostech.pegasusbackend.model.entities.Theme;
import br.com.lacostech.pegasusbackend.repositories.ThemeRepository;
import br.com.lacostech.pegasusbackend.services.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
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

    @Transactional
    public ThemeModel insert(final ThemeModel request) {
        Theme theme = new Theme();
        copyDataFromRequest(request, theme);

        theme = themeRepository.save(theme);
        return new ThemeModel(theme);
    }

    @Transactional
    public ThemeModel update(final Long id, final ThemeModel request) {
        Theme theme = getThemeById(id);
        copyDataFromRequest(request, theme);

        theme = themeRepository.save(theme);
        return new ThemeModel(theme);
    }

    private void copyDataFromRequest(final ThemeModel request, final Theme entity) {
        if (Objects.nonNull(request)) {
            entity.setName(request.getName());
        }
    }

    private Theme getThemeById(final Long id) {
        return themeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Theme id " + id + " not found"));
    }

}
