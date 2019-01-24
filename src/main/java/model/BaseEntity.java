package model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity implements BaseModel<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pk;

    @Override
    public Long getPK(){
        return pk;
    }

    public void setPK(Long pk) {
        this.pk = pk;
    }

    @Override
    public boolean isNew() {
        return pk == null;
    }

}
