// import { useState } from 'react'
import "./App.css";
import AuthModal from "./components/authentication/AuthModal";
import MainPage from "./components/layout/MainPage";
// import Modal from "./components/ui/Modal";
// import NewTask from "./components/ui/NewTask";
function App() {
	const TOKEN = localStorage.getItem("token");

	return (
		<div className="App">
			{/* <Modal>
				<NewTask />
			</Modal> */}
			{TOKEN === null ? <AuthModal /> : <MainPage TOKEN={TOKEN} />}
		</div>
	);
}

export default App;
