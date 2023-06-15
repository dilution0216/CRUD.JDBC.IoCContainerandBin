package com.sparta.memo.entity;

import com.sparta.memo.dto.MemoRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Memo {
    private Long id;
    private String username;
    private String contents;

    public Memo(MemoRequestDto requestDto) {
        // RequestDto 에서 Get 메서드를 사용해서 Username 이름과 contents 내용을 가지고 와서
        // 위의 Memo class 에 이 두개의 field 데이터를 넣어주면서 메모객체를 하나 만들어내는
        // 생성자를 만든 것.
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
    }
}