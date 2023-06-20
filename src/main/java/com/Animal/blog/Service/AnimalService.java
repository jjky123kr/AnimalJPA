package com.Animal.blog.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.Animal.blog.Model.Animal;
import com.Animal.blog.Model.Member;
import com.Animal.blog.Model.RoleType;
import com.Animal.blog.Model.UploadFile;
import com.Animal.blog.repository.AnimalRepository;
import com.Animal.blog.repository.UploadFileRepository;

import aj.org.objectweb.asm.Type;

@Service
public class AnimalService {

	@Autowired
	private AnimalRepository animalRepository;

	private UploadFileRepository uploadFileRepository;

	// 동물 등록
	@Transactional
	public void save(Animal animal, Member member) {
		animal.setCount(0);
		animal.setMember(member);
		System.out.println("save:호출");

		animalRepository.save(animal);
	}

	// 동물 목록
	@Transactional(readOnly = true)
	public Page<Animal> 글목록(Pageable pageable) {
		System.out.println("service 드러옴");
		return animalRepository.findAll(pageable);
	}

	// 글 상세 페이지
	@Transactional(readOnly = true)
	public Animal 글상세보기(int id) {
		return animalRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("글작성 실패:id를 찾을수 없습니다.");
		});
	}

	// 동물 삭제
	@Transactional
	public void delete(int id) {
		animalRepository.deleteById(id);
	}

	// 동물 정보 수정
	@Transactional
	public void update(int id, Animal requestAnimal) {
		Animal animal = animalRepository.findById(id)
				.orElseThrow(() -> {
					return new IllegalArgumentException("글작성 실패:id를 찾을수 없습니다.");
				});// 영속화
	     animal.setAge(requestAnimal.getAge());
	     animal.setKg(requestAnimal.getKg());
         animal.setNeutered(requestAnimal.getNeutered());
         animal.setContent(requestAnimal.getContent());
	}

}
