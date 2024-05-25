package com.example.Ecommerce.Entity;

import com.example.Ecommerce.token.Token;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Seller implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;
    @NotBlank(message="Please enter the first name")
    @Pattern(regexp="[A-Za-z\\s]+", message="First Name should contains alphabets only")
    private String firstname;
    @NotBlank(message="Please enter the last name")
    @Pattern(regexp="[A-Za-z\\s]+", message="First Name should contains alphabets only")
    private String lastname;
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$",message = "enter valid password")
    private String password;
    //	@NotBlank(message="Please enter your mobile Number")
//	@Pattern(regexp="[[6789]{1}[0-9]{9}]", message="Enter a valid Mobile Number")
    @Column(unique = true)
    private String mobile;
    @Email
    @Column(unique = true)
    @NotBlank(message = "please enter your mail id")
    private String email;




    @OneToMany(mappedBy = "seller", fetch = FetchType.EAGER)
    private List<Token> token;

    @OneToMany(fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Product> product;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return List.of(new SimpleGrantedAuthority("SELLER"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}

