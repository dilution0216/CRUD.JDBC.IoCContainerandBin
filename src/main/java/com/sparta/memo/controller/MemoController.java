package com.sparta.memo.controller;

import com.sparta.memo.dto.MemoRequestDto;
import com.sparta.memo.dto.MemoResponseDto;
import com.sparta.memo.entity.Memo;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController // html 을 따로 반환하지 않기 때문
@RequestMapping("/api") // 중복되는 api
public class MemoController {

    private final Map<Long, Memo> memoList = new HashMap<>();
    // Key(Long) 에 Id 값 넣어줘 구분,
    // Value(Memo) 에 실제데이터, 즉 실제 Memo 데이터 넣어주면 되어서 넣었음.

    @PostMapping("/memos")
    public MemoResponseDto createMemo(@RequestBody MemoRequestDto requestDto) {
        // RequestDto -> Entitiy
        Memo memo = new Memo(requestDto);

        // Memo Max ID Check
        Long maxId = memoList.size() > 0 ? Collections.max(memoList.keySet()) + 1 : 1; // 아니라면 데이터가 없으므로 1을 넣어줌
        // memoList.KeySet 호출하면 Map 에 가장 큰 Long(Key)값을 가져옴.
        memo.setId(maxId); // id 세팅

        // DB 저장
        memoList.put(memo.getId(), memo);

        // Entity -> ResponseDto
        MemoResponseDto memoResponseDto = new MemoResponseDto(memo);
        return memoResponseDto;
    }

    @GetMapping("/memos")
    public List<MemoResponseDto> getMemos() {
        // Map to List
        List<MemoResponseDto> responseList = memoList.values().stream() // values 를 하면 여러개가 나오는데, 이를 for문 처럼 돌려주는게 .stream()
                .map(MemoResponseDto::new).toList(); // map 을 통해 그것을 변환해줌. :new를 통해 하나씩 튀어 나오는 메모를 파라미터로 가지는 생성자를 찾음 그게 바로 MemoResponseDto 에 있는 this.xx들 즉 생성자가 수행이 되면서
                                                    // 하나의 MemoResponseDto 가 생성이 됌.
                                                    // 그것들을 모아서 MemoResponseDto 타입의 List 를 만들어주는 것 (toList 가)
        return responseList;
    }
}
