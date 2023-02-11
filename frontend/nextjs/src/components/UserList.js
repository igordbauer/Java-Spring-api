import { useEffect, useState } from "react";
import { Paper, Box, CardHeader } from "@mui/material";
import { getAllUsers } from "../controller/user-controller";
import UserItem from "./UserItem";

const UserList = () => {
  const [users, setUsers] = useState([]);
  const [isLoading, setIsLoading] = useState(true);

  useEffect(async () => {
    const load = async () => {
      const data = await getAllUsers();
      setUsers(data);
      setIsLoading(false);
    };
    load();
  }, []);

  return (
    <Paper
      elevation={10}
      sx={{
        mb: 2,
        p: 2,
        minWidth: "275px",
      }}
    >
      <CardHeader title="Users" />
      {!isLoading && (
        <Box>
          {users.map((u) => (
            <UserItem key={u._id} name={u.name} email={u.email} />
          ))}
        </Box>
      )}
    </Paper>
  );
};

export default UserList;
