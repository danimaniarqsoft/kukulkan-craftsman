package mx.infotec.dads.kukulkan.assets.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * not an ignored comment
 */
@Document(collection = "granularity")
public class Granularity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("name")
    private String name;

    @NotNull
    @Field("description")
    private String description;

    @NotNull
    @Field("brief_description")
    private String briefDescription;

    @Field("jhi_order")
    private Integer order;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Granularity name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public Granularity description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBriefDescription() {
        return briefDescription;
    }

    public Granularity briefDescription(String briefDescription) {
        this.briefDescription = briefDescription;
        return this;
    }

    public void setBriefDescription(String briefDescription) {
        this.briefDescription = briefDescription;
    }

    public Integer getOrder() {
        return order;
    }

    public Granularity order(Integer order) {
        this.order = order;
        return this;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Granularity granularity = (Granularity) o;
        if (granularity.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), granularity.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Granularity{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", briefDescription='" + getBriefDescription() + "'" +
            ", order='" + getOrder() + "'" +
            "}";
    }
}
