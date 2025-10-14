import Modal from "../ui/Modal";
import "./Board.css";
import BoardHeader from "./BoardHeader";
import BoardSidebar from "./BoardSidebar";
import BoardTaskList from "./BoardTaskList";
import NewTask from "./NewTask";

export default function Board({
	currBoardData,
	boardName,
	showCreateTask,
	setShowCreateTask,
	quantityOfTask,
}) {
	return (
		<div className="board-container">
			<BoardHeader
				boardName={boardName}
				boardMembers={currBoardData.members}
				currentBoardId={currBoardData.boardId}
			/>
			<div className="board__wrap-content">
				<div className="board__main-content">
					<BoardTaskList
						tasksData={currBoardData.boardTasks}
						setShowCreateTask={setShowCreateTask}
					/>
				</div>
				<BoardSidebar quantityOfTask={quantityOfTask} />
			</div>
			{showCreateTask && (
				<Modal>
					<NewTask
						currBoardData={currBoardData}
						setShowCreateTask={setShowCreateTask}
					/>
				</Modal>
			)}
		</div>
	);
}
