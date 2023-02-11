import React, { useCallback } from "react";
import Form from "../src/components/Form";
import UserList from "../src/components/UserList";
import { Container, Typography, Box } from "@mui/material";
import ProTip from "../src/ProTip";
import Link from "../src/Link";
import Copyright from "../src/Copyright";

export default function Index() {
  return (
    <Container maxWidth="md">
      <Box sx={{ my: 4 }}>
        <Form />
        <UserList />
      </Box>
    </Container>
  );
}
