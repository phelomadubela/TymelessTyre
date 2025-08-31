/*User Domain(Builder)
 * Author: Yanga Jilaji
 * Student number: 222582731
 * */

package za.co.tt.domain;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;


import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String firstName;
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;
    private String password;
    private String phone;

    @Column(nullable = false, updatable = false)
    private LocalDate createdAt = LocalDate.now();

    @Enumerated(EnumType.STRING)
    private UserRole role;
    @Column(nullable = false)
    private Boolean isActive = true;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Address> addresses;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Payment> payments;


//     @OneToMany(mappedBy = "user")
//     private List<Order> orders;

//     @OneToMany(mappedBy = "user")
//     private List<Reviews> reviews;


//      @OneToOne(mappedBy = "user")
//      @JoinColumn(name = "cartId", nullable = false)
//      private Cart cart;


    public User(){

    }

    private User(Builder builder){
        this.userId= builder.userId;
        this.firstName= builder.firstName;
        this.lastName= builder.lastName;
        this.email= builder.email;
        this.password= builder.password;
        this.phone= builder.phone;
        this.createdAt = builder.createdAt;
        this.role = builder.role;
        this.isActive = builder.isActive;
        this.addresses = builder.addresses;
        this.payments = builder.payments;
        //this.orders = builder.orders;
        //this.reviews = builder.reviews;
        //this.cart = builder.cart;
    }


    public Long getUserId() {

        return userId;
    }

    public String getFirstName() {

        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public UserRole getRole() {
        return role;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public List<Payment> getPayments() {
        return payments;
    }

//    public List<Order> getOrders(){
//        return orders;
//    }
//    public List<Reviews> getReviews(){
//        return reviews;
//    }
//    public Cart getCart(){
//        return  cart;
//    }


    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", role=" + role +
                ", isActive=" + isActive +
                ", createdAt=" + createdAt +
                ", addresses=" + addresses +
                ", payments=" + payments +
                //", orders=" + orders +
               // ", reviews=" + reviews +
               // ", cart=" + cart +
                '}';
    }

    public void setUserId(long userId) {

    }

    public static class Builder{
        private Long userId;
        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private String phone;
        private LocalDate createdAt;
        private UserRole role = UserRole.CUSTOMER;
        private Boolean isActive = true;
        private List<Address> addresses;
        private List<Payment> payments;
        //private List<Order> orders;
        //private List<Reviews> reviews;
       // private Cart cart;



        public Builder setUserId(Long userId) {
            this.userId = userId;
            return this;
        }
        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }
        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }
        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }
        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }
        public Builder setPhone(String phone) {
            this.phone = phone;
            return this;
        }
        public Builder setCreatedAt(LocalDate createdAt) {
            this.createdAt = createdAt;
            return this;
        }
        public Builder setRole(UserRole role) {
            this.role = role;
            return this;
        }
        public Builder setIsActive(Boolean isActive) {
            this.isActive = isActive;
            return this;
        }
        public Builder setAddresses(List<Address> addresses) {
            this.addresses = addresses;
            return this;
        }
        public Builder setPayments(List<Payment> payments){
            this.payments = payments;
            return  this;
        }
//        public Builder setOrder(List<Order> orders){
//            this.orders = orders;
//            return this;
//        }
//        public  Builder setReviews(List<Reviews> reviews){
//            this.reviews = reviews;
//            return this;
//        }
//        public Builder setCart(Cart cart){
//            this.cart = cart;
//            return this;
//        }

        public Builder copy(User user) {
            this.userId = user.userId;
            this.firstName = user.firstName;
            this.lastName = user.lastName;
            this.email = user.email;
            this.password = user.password;
            this.phone = user.phone;
            this.createdAt = user.createdAt;
            this.role = user.role;
            this.isActive = user.isActive;
            this.addresses = user.addresses;
            this.payments = user.payments;
            //this.orders = user.orders;
            //this.reviews = user.reviews;
            //this.cart = user.cart;
            return this;
        }

        public User build(){
            return new User(this);
        }
    }
}
