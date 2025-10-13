export default function BoardSidebar({ quantityOfTask }) {
	return (
		<div className="board__sidebar">
			<button className="toggle-my-task">show my task</button>
			<ul className="task-filter-list">
				<li className="task-filter-item">
					<button className="board__filter">
						All tasks: <p>{quantityOfTask}</p>
					</button>
				</li>
				<li className="task-filter-item">
					<button className="board__filter">In progress</button>
				</li>
				<li className="task-filter-item">
					<button className="board__filter">Completed</button>
				</li>
			</ul>
		</div>
	);
}
