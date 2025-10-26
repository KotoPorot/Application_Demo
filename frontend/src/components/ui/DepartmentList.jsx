import Modal from "./Modal";
import NewDepartment from "./NewDepartment";
import { useState, useContext } from "react";
import { BoardContext } from "../../context/Contexts";

export default function DepartmentList() {
	const [showCreateDepartment, setShowCreateDepartment] = useState(false);
	const { boardDepartments } = useContext(BoardContext);

	let departmentsName = "loading...";
	if (boardDepartments) {
		departmentsName = boardDepartments.map((boardDepartment) => (
			<button key={boardDepartment.id} className="department-link">
				{boardDepartment.name}
			</button>
		));
	}
	return (
		<div className="department-list">
			<h3>Departments</h3>
			{departmentsName}
			<button
				onClick={() => {
					setShowCreateDepartment(true);
				}}
				className="newBoard new"
			>
				Create new
			</button>
			{showCreateDepartment && (
				<Modal>
					<NewDepartment
						showCreateDepartment={showCreateDepartment}
						setShowCreateDepartment={setShowCreateDepartment}
					/>
				</Modal>
			)}
		</div>
	);
}
