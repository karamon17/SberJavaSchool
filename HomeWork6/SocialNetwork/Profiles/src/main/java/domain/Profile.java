package domain;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Profile {
    private Long id;

    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private String gender;
    private LocalDate birthday;
    private Set<Profile> friends = new HashSet<>();

}
