package lwc.com.springboot.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.Lob;

@Entity
@Table(name="wikimedia_recentchange")
@Getter
@Setter
public class WikimediaData  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String wikiEventData;

}
