import { Paper, CardHeader, Typography } from "@mui/material";

const UserItem = (props) => {
  return (
    <Paper elevation={5} sx={{ backgroundColor: "#6DEBAF", mb: 2 }}>
      <CardHeader title={props.name} subheader={props.email} />
    </Paper>
  );
};
export default UserItem;
