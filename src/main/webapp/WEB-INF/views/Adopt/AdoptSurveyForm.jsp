<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/AdminHeader.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<main>
	<h1 style="text-align: center;">입양 신청 작성</h1>
	<form>
		<input type="hidden" id="adoptId" value="${adopt.id}">

		<div class="container mt-5">
			<h3 class="mb-4">양식을 채워주세요</h3>
			<div class="ms-2 mt-5">
				<label> <strong>1.입양공고 제목</strong>
				</label> <input type="text" name="title" id="title" value="${adopt.title}" class="form-control" readonly="readonly">
			</div>

			<div class="ms-2 mt-5">
				<label for="name"><strong> 서명</strong></label> <input type="text" name="name" id="name" class="form-control">
			</div>
			
			<div class="ms-2 mt-5">
				<label for="sex"><strong> 성별</strong></label> 
				<select class="select" name="sex" id="sex">
						<option value="남자">남자</option>
						<option value="여자">여자</option>
				</select>
			</div>


			<div class="ms-2 mt-5">
				<label for="age"><strong>연령</strong></label> <input type="text" class="form-control" name="age" id="age">
			</div>

			<div class="ms-2 mt-5">
				<label for="tel"><strong>전화번호</strong></label> <input type="text" class="form-control" name="tel" id="tel">
			</div>

			<div class="ms-2 mt-5">
				<label for="email"><strong>이메일</strong></label> <input type="text" class="form-control" name="email" id="email">
			</div>

			<div class="ms-2 mt-5">
				<label for="Atime"><strong>통화가능한 시간</strong></label>
			</div>
			<input type="text" class="form-control" name="Atime" id="Atime">

			<div class="ms-5 mt-5">
				<label><strong>사시는 지역</strong></label> <input type="text" class="form-control" name="Address" id="Address" placeholder="(예:서울,경기,강원...등)">
			</div>

			<div class="ms-5 mt-5">
				<label><strong>신청인 직업</strong></label> <input type="text" class="form-control" name="job" id="job">
			</div>
			
			<div class="ms-2 mt-5">
				<label for="wedding"><strong> 결혼여부</strong></label> 
				<select class="select" name="wedding" id="wedding">
						<option value="미혼">미혼</option>
						<option value="기혼">기혼</option>
				</select>
			</div>
			

			<div class="ms-5 mt-5">
				<label for="Q1"><strong>1.예전에 반려동물을 키우신 적이 있나요?</strong></label>
				<div>
					<p>(만일 있다면 어떤종류의 동물인지, 얼마나 오래 키우셨는지 , 지금은 어떻게 되었는지 적어주세요.)</p>
					<textarea rows="4" cols="100" name="q1" id="q1"></textarea>
				</div>
			</div>

			<div class="ms-5 mt-5">
				<strong>2.귀하의 가족은 몇 명인가요?</strong> 
				<input type="text" class="form-control" name="q2" id="q2">
			</div>

			<div class="ms-5 mt-5">
				<label for="q3"> 
				<strong>3. 거주하고 계신 주택 형태에 알려주세요</strong>
				</label>
				<div>
					<input type="text" class="form-control" name="q3" id="q3" placeholder="예시) 아파트,단독주택,다세대/빌라,기타">
				</div>

				<div class="ms-5 mt-5">
					<label><strong>4.가족들은 입양에 대해서 모두 찬성하시나요?</strong></label>
					<div>
						<input type="text" class="form-control" id="q4" name="q4" placeholder="예시)모두찬성, 부분찬성, 신청자만 찬성">
					</div>

					<div class="ms-5 mt-5">
						<label><strong>5. 입양을 원하시는 이유는 무엇인가요?</strong></label>
						<div>
							<textarea rows="4" cols="100" name="q5" id="q5"></textarea>
						</div>
					</div>

					<div class="ms-2 mt-5">
						<label><strong>6.만약 댁에서 새로운 아기가 출생할 경우 입양된 동물을 계속 키우실 수 있겠습니까?</strong></label>
						<div>
							<input type="text" class="form-control" id="q6" name="q6" placeholder="예시)예,아니오">
						</div>
					</div>

					<div class="ms-5 mt-5">
						<label><strong>8.귀하와 가족의 부재시(여행,명정,휴가)반려동물을 어떻게 관리하실 예정이신가요?</strong></label>
						<div>
							<textarea rows="4" cols="100" name="q7" id="q7"></textarea>
						</div>
					</div>
					<div class="ms-5 mt-5">
						<label><strong>9. 아래 사항에 대해 대략 어느 정도의 비용이 들어갈지 예상되는 비용을 기입해주세요. </strong></label>
						<p>1년 동안의 예방접종비용</p>
						<input type="text" class="form-control" name="q8" id="q8">
						<p>1개월 동안의 사료 및 양육비용</p>
						<input type="text" class="form-control" name="q9" id="q9">
					</div>


					<div class="ms-5 mt-5">
						<label for="Q10"><strong>반려동물의 수명은 15년이상 됩니다. 10년이상 키울 수 있으십니까? 만약의 경우 입양 동물을 키우다가 더이상 양육할 여건이 되지 못할 시, 제3자에게 양도하지 않고 본 단체로 돌려보내 주실 것에 동의하십니까? (반드시 동의하셔야 합니다.)</strong></label>
						<div>
							<input type="radio" id="Q10" name="Q10" value="yes"> 
							<label>예</label> <input type="radio" id="q10" name="q10" value="no"> <label>아니오</label>
						</div>
					</div>

					<div class="ms-5 mt-5">
						<label for="Q11"> <strong>13. 반려동물을 입양하시려면, 입양비(유기견의 구조와 치료, 구제비로 사용됩니다)를 납부하셔야 하며 불임 수술 시행에도 동의하셔야 합니다. 동의하십니까?</strong></label>
						<div>
							<input type="radio" id="q11" name="q11" value="yes"> 
							<label>예</label> 
							<input type="radio" id="q11" name="q11" value="no"> 
							<label>아니오</label>
						</div>
					</div>

					<div class="ms-5 mt-5">
						<label><strong>14. 그 외에 입양 신청에 관해 덧붙이고자 하시는 말씀이 있으시면 적어주시기 바랍니다</strong></label>
						<div>
							<textarea rows="4" cols="100" name="q12" id="q12"></textarea>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>

	<div style="text-align: center;">
		<button id="btn-save" type="button" class="btn btn-outline-warning" style="margin-bottom: 100px;">입양신청</button>
	</div>
</main>


<script src="/js/Admin/AdoptSurvey.js"></script>
<%@ include file="../layout/AdminFooter.jsp"%>




















