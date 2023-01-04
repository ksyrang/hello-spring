package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach//하나의 테스트를 진행하고 나면 무조건 실행되는 언옵테이션
    public void afterEacth(){
        repository.clearStore();
    }

    @Test//테스트의 최소단위
    public void save(){
        Member member =new Member();

        member.setName("String");

        repository.save(member);

        Member result = repository .findById(member.getId()).get();

//        System.out.println("result = "+(result == member));
//        Assertions.assertEquals(member,result);
          assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member mem1 = new Member();
        mem1.setName("sp1");
        repository.save(mem1);

        Member mem2 = new Member();
        mem2.setName("sp2");
        repository.save(mem2);


        Member result = repository.findByName("sp1").get();

        assertThat(result).isEqualTo(mem1);
    }

    @Test
    public void findAll(){
        Member mem1 = new Member();
        mem1.setName("sp1");
        repository.save(mem1);

        Member mem2 = new Member();
        mem2.setName("sp2");
        repository.save(mem2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
