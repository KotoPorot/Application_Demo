import Task from "./Task";
export default function BoardTaskList({ tasksData, setShowCreateTask }) {
	let taskList = "loading...";
	if (tasksData) {
		taskList = tasksData.map((data) => <Task key={data.id} taskData={data} />);
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
