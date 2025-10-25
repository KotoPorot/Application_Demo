import Modal from "./Modal";
import NewDepartment from "./NewDepartment";
import { useState } from "react";

export default function DepartmentList({
	departmentsData,
	currentBoardId,
	setRefreshBoardData,
}) {
	const [showCreateDepartment, setShowCreateDepartment] = useState(false);

	let departmentsName = "loading...";
	if (departmentsData) {
		departmentsName = departmentsData.map((departmentsData) => (
			<button key={departmentsData.id} className="department-link">
				{departmentsData.name}
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
						currentBoardId={currentBoardId}
						showCreateDepartment={showCreateDepartment}
						setShowCreateDepartment={setShowCreateDepartment}
						setRefreshBoardData={setRefreshBoardData}
					/>
				</Modal>
			)}
		</div>
	);
}
