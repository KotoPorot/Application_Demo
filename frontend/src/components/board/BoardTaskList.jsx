import Task from "./Task";
import { useContext } from "react";
import { BoardContext } from "../../context/Contexts";

export default function BoardTaskList({ setShowCreateTask }) {
	const { boardTasks } = useContext(BoardContext);
	let taskList = "loading...";
	if (boardTasks) {
		taskList = boardTasks.map((data) => <Task key={data.id} taskData={data} />);
	}
	return (
		<ul className="task-list">
			{taskList}
			<button onClick={() => setShowCreateTask(true)} className="newTask new">
				Create new
			</button>
		</ul>
	);
}
