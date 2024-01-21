<script>
	import { goto } from "$app/navigation";
	import axios from "axios";

	axios.defaults.baseURL = "http://localhost:8080";
	axios.defaults.withCredentials = true;

	let signupId = "";
	let signupPassword = "";

	function signup() {
		axios
			.post("/signup", {
				id: signupId,
				password: signupPassword,
			})
			.then(() => alert("회원가입 완료! 로그인 해주세요!"));
	}

	let loginId = "";
	let loginPassword = "";

	function login() {
		const form = new FormData();
		form.append("id", loginId);
		form.append("password", loginPassword);

		axios
			.post("/login", form, {
				headers: {
					"Content-Type": "multipart/form-data",
				},
			})
			.then(() => goto("/chat", { replaceState: true }));
	}
</script>

<h1>회원가입</h1>
<form>
	id : <input type="text" bind:value={signupId} /> <br />
	pw : <input type="password" bind:value={signupPassword} />
	<button on:click={signup}> 회원가입 </button>
</form>

<br />
<hr />
<br />

<h1>로그인</h1>
<form>
	id : <input type="text" bind:value={loginId} /> <br />
	pw : <input type="password" bind:value={loginPassword} />
	<button on:click={login}> 로그인 </button>
</form>
