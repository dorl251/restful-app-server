package com.example.restfulappserver.user;

import com.fasterxml.jackson.annotation.JsonFilter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
//@JsonIgnoreProperties(value={"password", "ssn"})
@NoArgsConstructor
//@JsonFilter("UserInfo")
@ApiModel(description = "유저 정보 도메인")
@Entity
public class User {
    //User(){}
    @Id
    private Integer id;

    @Size(min=4, message = "Name 4글자 이상 입력 해야함")
    @ApiModelProperty(notes = "사용자 이름")
    private String name;
    @Past
    @ApiModelProperty(notes = "사용자 등록일")
    private Date joinDate;
    @ApiModelProperty(notes = "사용자의 패스워드")
    private String password;
    @ApiModelProperty(notes = "사용자 주민번호")
    private String ssn;

    @OneToMany(mappedBy = "user")
    private List<UserPost> posts;

    public User(int id, String name, Date joinDate, String password, String ssn) {
        this.id = id;
        this.name = name;
        this.joinDate = joinDate;
        this.password = password;
        this.ssn = ssn;
    }
}
