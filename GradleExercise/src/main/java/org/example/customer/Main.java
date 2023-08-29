package org.example.customer;

import org.example.person.Person;
import org.example.person.PersonDto;
import org.example.person.PersonMapStruct;

// PersonDto → Person 변환
// - 필드 이름이 다를 수 있음

public class Main {

    public static void main(String[] args) {

        // PersonDto to Person
        PersonDto personDto = new PersonDto();
        personDto.setName("John Doe");
        personDto.setAge(30);
        personDto.setAddress("123 Street, City");
        personDto.setEmail("john.doe@example.com");
        personDto.setPhone("123-456-7890");
        personDto.setGender("Male");
        personDto.setNationality("American");
        personDto.setEducation("Bachelor's Degree");

        System.out.println(personDto.toString());


        Person person = new Person();
        person.setFullName(personDto.getName());
        person.setYears(personDto.getAge());

        System.out.println(person.toString());


        Person personMapping = PersonMapStruct.INSTANCE.personDtotoPerson(personDto);

        System.out.println("personMapping: " + personMapping);
    }
}
