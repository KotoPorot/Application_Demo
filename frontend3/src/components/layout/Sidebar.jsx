import BoardsList from "../ui/BoardsList";
import DepartmentList from "../ui/DepartmentList";
export default function Sidebar({
	userBoardsData,
	currBoardData,
	showModal,
	setShowModal,
	currentBoardId,
	setCurrentBoardId,
	setCurrentDepartmentId,
}) {
	return (
		<aside className="sidebar">
			<BoardsList
				userBoardsData={userBoardsData}
				currBoardData={currBoardData}
				showModal={showModal}
				setShowModal={setShowModal}
				setCurrentBoardId={setCurrentBoardId}
				currentBoardId={currentBoardId}
			/>
			<DepartmentList
				departmentsData={currBoardData.boardDepartments}
				currentBoardId={currentBoardId}
				setCurrentDepartmentId={setCurrentDepartmentId}
			/>
		</aside>
	);
}
