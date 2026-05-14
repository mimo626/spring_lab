package com.example.springlab;

import com.example.springlab.domain.entity.Friend;
import com.example.springlab.repository.FriendRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class FriendRepositoryTest {
    @Autowired
    FriendRepository friendRepository;

    @BeforeEach
    void setUp() {
        System.out.println("=".repeat(15));
    }
    @Test
    @Order(1)
    void create() {
        Friend friend1 = Friend.builder().fname("둘리").fage(10).build();
        Friend friend2 = Friend.builder().fname("또우치").fage(13).build();
        Friend friend3 = Friend.builder().fname("홍길동").fage(26).build();
        Friend friend4 = Friend.builder().fname("고길동").fage(28).build();

        friendRepository.save(friend1);
        friendRepository.save(friend2);
        friendRepository.save(friend3);
        friendRepository.save(friend4);

        List<Friend> list = friendRepository.findAll();

        System.out.print("생성 후 데이터: ");
        list.stream().forEach(System.out::println);
    }
    @Test
    @Order(2)
    void update() {
        Friend friend = friendRepository.findAll().stream().findFirst().get();

        friend.setFage(25);
        Friend updatedFriend = friendRepository.save(friend);

        System.out.println("수정된 데이터: " + updatedFriend.toString());
    }
    @Test
    @Order(3)
    void delete() {
        Friend friend = friendRepository.findAll().stream().findFirst().get();
        friendRepository.delete(friend);

        System.out.print("삭제 후 데이터: ");
        friendRepository.findAll().stream().forEach(System.out::println);
    }
}
