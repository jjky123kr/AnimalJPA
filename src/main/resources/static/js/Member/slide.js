let slidewrapper = $('.slidewrapper'),
    slides = slidewrapper.find('li'),
    currentIdx = 0,
    timer = undefined,
    pager = slidewrapper.find('.pager a');

// 슬라이드 배치 

slides.each(function(idx){
	$(this).css({left:`${idx*100}%`});
});

// 페이저 슬라이드 작동시키기
pager.click(function(e){
	e.preventDefault();
	let idx =$(this).index();
	moveSlide(idx);
});

//moveSlide 함수
function moveSlide(i){ 
	if(currentIdx === i)return;
	let currentSlide = slides.eq(currentIdx);
	let nextSlide = slides.eq(i);
	//다음슬라이드 순간 leaf 100% animate 0
	//현재슬라이드 순간 leaf 0%   animate -100%
	
	nextSlide.css({left:'100%'}).animate({left:'0%'});
	currentSlide.css({left:'0%'}).animate({left:'-100%'});
	
	currentIdx = i;
	//모든 페이저에서 active 제거 현재 번호에 맞게 active 추가
	
	pager.removeClass('active');
	pager.eq(currentIdx).addClass('active');
	
	
}
function autoSlide(){
	if(timer == undefined){
		timer = setInterval(()=>{
			//let ni = (currentIdx + 1)%slides.length;
			let ni = currentIdx + 1 ;
			if(ni===slides.length){
				ni=0;
			}
			moveSlide(ni)
		},3000);
		console.log(timer);
	}	
}
autoSlide();
slidewrapper.mouseenter(function(){
	clearInterval(timer);
	timer = undefined;
	console.log(timer);
	
})
.mouseleave(function(){
	autoSlide();
});