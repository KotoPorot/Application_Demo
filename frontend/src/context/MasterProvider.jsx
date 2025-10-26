import UserProvider from "./UserProvider";
import BoardProvider from "./BoardProvider";

export const MasterContext = ({ children }) => {
	return (
		<UserProvider>
			<BoardProvider>{children}</BoardProvider>
		</UserProvider>
	);
};
