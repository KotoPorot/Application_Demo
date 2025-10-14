import Modal from "../ui/Modal";
import { useState } from "react";
export default function Task({ taskData }) {
	const [showTask, setShowTask] = useState(false);
	return (
		<>
			<li>
				<button className="task" onClick={() => setShowTask(true)}>
					{taskData.name}
				</button>
			</li>
			{showTask && (
				<Modal>
					{console.log(taskData)}
					<h2 className="modal-title">{taskData.name}</h2>
					<h4 className="sub-title">Description:</h4>
					<p>{taskData.description}</p>
					<h4 className="sub-title">Department:</h4>
					<p>{taskData.department}</p>
					<h4 className="sub-title">Status:</h4>
					<p>{taskData.taskStatus}</p>
					<button
						className="close-btn"
						onClick={() => setShowTask(false)}
						type="button"
					>
						Close
					</button>
				</Modal>
			)}
		</>
	);
}
