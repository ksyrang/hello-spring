package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

//    MemberService memSvc = new MemberService();
//    MemoryMemberRepository meberRepository = new MemoryMemberRepository();
MemberService memSvc;
    MemoryMemberRepository meberRepository;
    @BeforeEach //테스트 함수들이 동작전에 행동하는 함수
    public void beforeEach(){
        meberRepository = new MemoryMemberRepository();
        memSvc = new MemberService(meberRepository);
        //위와 같은 형태를 의존성 주입이라 한다.
    }
    @AfterEach //모든 테스트 함수들이 동작을 완료한 후에 행동하는 함수
    public  void afterEach(){
        meberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given : 무슨 데이터를 줄건지
        Member mem = new Member();
        mem.setName("spring");


        //when : 언제 행동할지

        Long saveId = memSvc.join(mem);

        //then : 무슨 결과를 원하는지
        Member findMem = memSvc.findOne(saveId).get();

        Assertions.assertThat(mem.getName()).isEqualTo(findMem.getName());


    }
    @Test
    public void 중복_회원_예외() throws Exception {
        //Given
        Member member1 = new Member();
        member1.setName("spring");
        Member member2 = new Member();
        member2.setName("spring"); //When
        memSvc.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memSvc.join(member2));//예외가 발생해야 한다.
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

    @Test
    void 회원리스트() {
    }

    @Test
    void 회원정보() {
    }
}