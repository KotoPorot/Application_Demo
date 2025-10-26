import BoardsList from "../ui/BoardsList";
import DepartmentList from "../ui/DepartmentList";
import NewBoard from "../ui/NewBoard";
import Modal from "../ui/Modal";
import { UserContext } from "../../context/Contexts";
import { useContext } from "react";

export default function Sidebar({ showModal, setShowModal }) {
	const { currentBoardId } = useContext(UserContext);

	return (
		<aside className="sidebar">
			{!currentBoardId && (
				<Modal>
					<NewBoard setShowModal={setShowModal} closeButton={false} />
				</Modal>
			)}
			<BoardsList showModal={showModal} setShowModal={setShowModal} />
			<DepartmentList />
		</aside>
	);
}
