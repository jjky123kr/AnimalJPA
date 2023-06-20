<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/AdminHeader.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<main>
	<h1 style="text-align: center;">입양 신청서</h1>
	<form>
	<input type="hidden" id="id" value="${survey.id}">
	
		<div class="container mt-5">
			
			
			<div class="ms-2 mt-5">
				<label for="name"><strong>입양동물 번호</strong></label> 
				<input type="text" value="${survey.adopt.id}" class="form-control" readonly="readonly">
			</div>
			
			<div class="ms-2 mt-5">
				<label> <strong>1.입양공고 제목</strong>
				</label> <input type="text" value="${survey.adopt.title}" class="form-control" readonly="readonly">
			</div>
			
			

			<div class="ms-2 mt-5">
				<label for="name"><strong>서명</strong></label> 
				<input type="text" value="${survey.name}" class="form-control" readonly="readonly">
			</div>
			
			<div class="ms-2 mt-5">
				<label for="name"><strong>성별</strong></label> 
				<input type="text" value="${survey.sex}" class="form-control" readonly="readonly">
			</div>
					

			<div class="ms-2 mt-5">
				<label for="age">
				<strong>연령</strong></label>
				 <input type="text" class="form-control" value="${survey.age}" readonly="readonly">
			</div>

			<div class="ms-2 mt-5">
				<label for="tel"><strong>전화번호</strong></label>
				 <input type="text" class="form-control"value="${survey.tel}"  readonly="readonly">
			</div>

			<div class="ms-2 mt-5">
				<label for="email"><strong>이메일</strong></label> 
				<input type="text" class="form-control" value="${survey.email}" readonly="readonly">
			</div>

			<div class="ms-2 mt-5">
				<label for="Atime"><strong>통화가능한 시간</strong></label>
			</div>
			<input type="text" class="form-control" value="${survey.atime}" readonly="readonly">

			<div class="ms-5 mt-5">
				<label><strong>사시는 지역</strong></label> 
				<input type="text" class="form-control" value="${survey.address}" readonly="readonly">
			</div>

			<div class="ms-5 mt-5">
				<label><strong>신청인 직업</strong></label> 
				<input type="text" class="form-control" value="${survey.job}" readonly="readonly">
			</div>
			
			<div class="ms-5 mt-5">
				<label><strong>결혼여부</strong></label> 
				<input type="text" class="form-control" value="${survey.wedding}" readonly="readonly">
			</div>
					

			<div class="ms-5 mt-5">
				<label for="Q1"><strong>1.예전에 반려동물을 키우신 적이 있나요?</strong></label>
				<div>
					<p>(만일 있다면 어떤종류의 동물인지, 얼마나 오래 키우셨는지 , 지금은 어떻게 되었는지 적어주세요.)</p>
					<textarea rows="4" cols="100" name="q1" id="q1" readonly="readonly" >${survey.q1}</textarea>
				</div>
			</div>

			<div class="ms-5 mt-5">
				<strong>2.귀하의 가족은 몇 명인가요?</strong> 
				<input type="text" class="form-control" name="q2" id="q2" value="${survey.q2}" readonly="readonly">
			</div>

			<div class="ms-5 mt-5">
				<label for="q3"> 
				<strong>3. 거주하고 계신 주택 형태에 알려주세요</strong>
				</label>
				<div>
					<input type="text" class="form-control" name="q3" id="q3" value="${survey.q3}" readonly="readonly">
				</div>

				<div class="ms-5 mt-5">
					<label><strong>4.가족들은 입양에 대해서 모두 찬성하시나요?</strong></label>
					<div>
						<input type="text" class="form-control" id="q4" name="q4" value="${survey.q4}" readonly="readonly">
					</div>

					<div class="ms-5 mt-5">
						<label><strong>5. 입양을 원하시는 이유는 무엇인가요?</strong></label>
						<div>
							<textarea rows="4" cols="100" name="q5" id="q5" readonly="readonly" >${survey.q5}</textarea>
						</div>
					</div>

					<div class="ms-2 mt-5">
						<label><strong>6.만약 댁에서 새로운 아기가 출생할 경우 입양된 동물을 계속 키우실 수 있겠습니까?</strong></label>
						<div>
							<input type="text" class="form-control" id="q6" name="q6" value="${survey.q6}" readonly="readonly" >
						</div>
					</div>

					<div class="ms-5 mt-5">
						<label><strong>8.귀하와 가족의 부재시(여행,명정,휴가)반려동물을 어떻게 관리하실 예정이신가요?</strong></label>
						<div>
							<textarea rows="4" cols="100" name="q7" id="q7" readonly="readonly" >${survey.q7}</textarea>
						</div>
					</div>
					<div class="ms-5 mt-5">
						<label><strong>9. 아래 사항에 대해 대략 어느 정도의 비용이 들어갈지 예상되는 비용을 기입해주세요. </strong></label>
						<p>1년 동안의 예방접종비용</p>
						<input type="text" class="form-control" name="q8" id="q8" value="${survey.q8}" readonly="readonly" >
						<p>1개월 동안의 사료 및 양육비용</p>
						<input type="text" class="form-control" name="q9" id="q9" value="${survey.q9}" readonly="readonly" >
					</div>


					<div class="ms-5 mt-5">
						<label for="Q10"><strong>반려동물의 수명은 15년이상 됩니다. 10년이상 키울 수 있으십니까? 만약의 경우 입양 동물을 키우다가 더이상 양육할 여건이 되지 못할 시, 제3자에게 양도하지 않고 본 단체로 돌려보내 주실 것에 동의하십니까? (반드시 동의하셔야 합니다.)</strong></label>
						<div>
							<input type="text" class="form-control" name="q10" id="q10" value="${survey.q10}" readonly="readonly">
						</div>
					</div>

					<div class="ms-5 mt-5">
						<label for="Q11"> <strong>13. 반려동물을 입양하시려면, 입양비(유기견의 구조와 치료, 구제비로 사용됩니다)를 납부하셔야 하며 불임 수술 시행에도 동의하셔야 합니다. 동의하십니까?</strong></label>
						<input type="text" class="form-control" name="q11" id="q11" value="${survey.q11}" readonly="readonly" >
					</div>

					<div class="ms-5 mt-5">
						<label><strong>14. 그 외에 입양 신청에 관해 덧붙이고자 하시는 말씀이 있으시면 적어주시기 바랍니다</strong></label>
						<div>
							<textarea rows="4" cols="100" name="q12" id="q12" readonly="readonly" >${survey.q12}</textarea>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	<div style="text-align: center;">
		<button id="btn-delete" class="btn btn-warning" style="margin-bottom:100px;">삭제</button>
	</div>
</main>

<script src="/js/Admin/AdoptSurvey.js"></script>
<%@ include file="../layout/AdminFooter.jsp"%>




















