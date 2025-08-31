package za.co.tt.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long addressId;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "province")
    private String province;

    @Column(name = "postal_code")
    private Integer postalCode;

    @Column(name = "country")
    private String country;

    @Column(name = "is_default")
    private Boolean isDefault = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDate createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDate updatedAt;

    protected Address() {}

    private Address(Builder builder) {
        this.addressId = builder.addressId;
        this.street = builder.street;
        this.city = builder.city;
        this.province = builder.province;
        this.postalCode = builder.postalCode;
        this.country = builder.country;
        this.isDefault = builder.isDefault;
        this.user = builder.user;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
    }

    public Long getAddressId() { return addressId; }
    public String getStreet() { return street; }
    public String getCity() { return city; }
    public String getProvince() { return province; }
    public Integer getPostalCode() { return postalCode; }
    public String getCountry() { return country; }
    public Boolean getIsDefault() { return isDefault; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public LocalDate getCreatedAt() { return createdAt; }
    public LocalDate getUpdatedAt() { return updatedAt; }

    @Override
    public String toString() {
        return "Address{" +
                "addressId=" + addressId +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", postalCode=" + postalCode +
                ", country='" + country + '\'' +
                ", isDefault=" + isDefault +
                ", user=" + (user != null ? user.getUserId() : null) +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    public void setAddressId(Long addressId) {

    }


    public static class Builder {
        private Long addressId;
        private String street;
        private String city;
        private String province;
        private Integer postalCode;
        private String country;
        private Boolean isDefault = false;
        private User user;
        private LocalDate createdAt;
        private LocalDate updatedAt;

        public Builder setAddressId(Long addressId) { this.addressId = addressId; return this; }
        public Builder setStreet(String street) { this.street = street; return this; }
        public Builder setCity(String city) { this.city = city; return this; }
        public Builder setProvince(String province) { this.province = province; return this; }
        public Builder setPostalCode(Integer postalCode) { this.postalCode = postalCode; return this; }
        public Builder setCountry(String country) { this.country = country; return this; }
        public Builder setIsDefault(Boolean isDefault) { this.isDefault = isDefault; return this; }
        public Builder setUser(User user) { this.user = user; return this; }
        public Builder setCreatedAt(LocalDate createdAt) { this.createdAt = createdAt; return this; }
        public Builder setUpdatedAt(LocalDate updatedAt) { this.updatedAt = updatedAt; return this; }

        public Builder copy(Address address) {
            this.addressId = address.addressId;
            this.street = address.street;
            this.city = address.city;
            this.province = address.province;
            this.postalCode = address.postalCode;
            this.country = address.country;
            this.isDefault = address.isDefault;
            this.user = address.user;
            this.createdAt = address.createdAt;
            this.updatedAt = address.updatedAt;
            return this;
        }

        public Address build() {
            return new Address(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return Objects.equals(addressId, address.addressId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressId);
    }
}
