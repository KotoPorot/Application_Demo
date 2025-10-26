import React from "react";
import "./MainPage.css";
import Sidebar from "./Sidebar";
import Header from "./Header";
import Board from "../board/Board";
import { MasterContext } from "../../context/MasterProvider";

export default function MainPage() {
	const [showCreateTask, setShowCreateTask] = React.useState(false);
	const [showModal, setShowModal] = React.useState(false);

	return (
		<MasterContext>
			<Header />
			<div className="container">
				<Sidebar showModal={showModal} setShowModal={setShowModal} />
				<Board
					showCreateTask={showCreateTask}
					setShowCreateTask={setShowCreateTask}
				/>
			</div>
		</MasterContext>
	);
}
