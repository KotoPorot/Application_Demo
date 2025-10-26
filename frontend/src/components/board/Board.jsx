import Modal from "../ui/Modal";
import "./Board.css";
import BoardHeader from "./BoardHeader";
import BoardSidebar from "./BoardSidebar";
import BoardTaskList from "./BoardTaskList";
import NewTask from "./NewTask";

export default function Board({ showCreateTask, setShowCreateTask }) {
	return (
		<div className="board-container">
			<BoardHeader />
			<div className="board__wrap-content">
				<div className="board__main-content">
					<BoardTaskList setShowCreateTask={setShowCreateTask} />
				</div>
				<BoardSidebar />
			</div>
			{showCreateTask && (
				<Modal>
					<NewTask setShowCreateTask={setShowCreateTask} />
				</Modal>
			)}
		</div>
	);
}
