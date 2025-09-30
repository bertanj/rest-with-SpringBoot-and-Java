package br.com.bertanj.data.dto.v1;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;


@JsonPropertyOrder({ "id", "author", "title", "launch_date", "price" })
@JacksonXmlRootElement(localName = "book")
public class BookDTO extends RepresentationModel<BookDTO> {

    private static final long serialVersionUID = 1L;

    private Long id;
    @JsonProperty("title")
    @JacksonXmlProperty(localName = "title")
    private String title;
    @JsonProperty("author")
    @JacksonXmlProperty(localName = "author")
    private String author;
    @JsonProperty("launch_date")
    @JacksonXmlProperty(localName = "launch_date")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date launchDate;
    @JsonProperty("price")
    @JacksonXmlProperty(localName = "price")
    private BigDecimal price;

    private String sensitiveData;

    public BookDTO(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(Date lauchDate) {
        this.launchDate = lauchDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getSensitiveData() {
        return sensitiveData;
    }

    public void setSensitiveData(String sensitiveData) {
        this.sensitiveData = sensitiveData;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BookDTO bookDTO = (BookDTO) o;
        return Objects.equals(getId(), bookDTO.getId()) && Objects.equals(getTitle(), bookDTO.getTitle()) && Objects.equals(getAuthor(), bookDTO.getAuthor()) && Objects.equals(getLaunchDate(), bookDTO.getLaunchDate()) && Objects.equals(getPrice(), bookDTO.getPrice()) && Objects.equals(getSensitiveData(), bookDTO.getSensitiveData());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId(), getTitle(), getAuthor(), getLaunchDate(), getPrice(), getSensitiveData());
    }
}
