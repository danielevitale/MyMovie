package it.developer.film.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter @Setter @NoArgsConstructor
public class WorkerImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable=false)
    private String filename;

    @Column(nullable=false)
    private String filetype; // mime type. Es. "image/jpg", "image/gif", "image/png"

    @Column(nullable=false)
    @Lob
    private byte[] data;

    public WorkerImg(String filename, String filetype, byte[] data) {
        this.filename = filename;
        this.filetype = filetype;
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkerImg workerImg = (WorkerImg) o;
        return id == workerImg.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
