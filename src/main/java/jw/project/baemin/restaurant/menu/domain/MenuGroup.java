package jw.project.baemin.restaurant.menu.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import jw.project.baemin.restaurant.menu.presentation.request.UpdateMenuGroupRequest;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class MenuGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Integer priority;

    private Long shopId;

    @OneToMany(mappedBy = "menuGroup", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Menu> menus = new ArrayList<>();

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public void update(UpdateMenuGroupRequest request) {
        this.name = request.name();
        this.description = request.description();
        this.priority = request.priority();
    }

    public void addMenu(Menu menu) {
        menus.add(menu);
    }
}
