package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long squence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++squence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
        //자바의 stream과 람다 이용 해당 반환은 Optional이 기본으로 들어가 있다.
//        return Optional.empty();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());//저장객체의 요소들을 반환
    }
}
