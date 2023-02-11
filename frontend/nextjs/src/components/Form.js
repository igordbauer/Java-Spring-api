import { VALIDATOR_REQUIRE, VALIDATOR_EMAIL } from "./shared/util/validators";
import {
  CardHeader,
  CardContent,
  CardActions,
  Paper,
  Button,
} from "@mui/material";
import useForm from "./shared/hooks/useForm";
import CustomInput from "./shared/FormComponents/CustomInput";
import { createUser } from "../controller/user-controller";

const initialInputValues = {
  name: {
    value: "",
    isValid: false,
  },
  email: {
    value: "",
    isValid: false,
  },
};

const Form = () => {
  const [formsState, inputHandler] = useForm(initialInputValues, false);

  const submitHandler = async (e) => {
    e.preventDefault();
    await createUser(formsState);
  };
  return (
    <Paper
      elevation={10}
      sx={{
        p: 2,
        mb: 2,
        minWidth: "275px",
      }}
    >
      <form onSubmit={submitHandler}>
        <CardHeader title="Create User" />
        <CardContent>
          <CustomInput
            inputId="name"
            type="text"
            initialValidity={false}
            initialValue={formsState.inputs.name.value}
            label="Name"
            placeholder="Please enter your name"
            validators={[VALIDATOR_REQUIRE()]}
            sx={{ width: 1 }}
            errorText="Please enter a valid Name"
            onChangeInput={inputHandler}
          />
          <CustomInput
            inputId="email"
            type="e-mail"
            initialValue={formsState.inputs.email.value}
            initialValidity={false}
            label="E-mail"
            placeholder="Please enter your email"
            validators={[VALIDATOR_REQUIRE(), VALIDATOR_EMAIL()]}
            sx={{ width: 1, my: 2 }}
            errorText="Please enter a valid email"
            onChangeInput={inputHandler}
          />
        </CardContent>
        <CardActions sx={{ m: 1, display: "flex", justifyContent: "flex-end" }}>
          <Button
            disabled={!formsState.isValid}
            variant="contained"
            type="submit"
          >
            Submit
          </Button>
        </CardActions>
      </form>
    </Paper>
  );
};

export default Form;
