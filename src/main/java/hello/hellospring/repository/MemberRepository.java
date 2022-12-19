package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);//Null 데이터를 반환하는 것을 막기 위함 자바8 기능
    Optional<Member> findByName(String name);
    List<Member> findAll();

    void clearStore();
}
