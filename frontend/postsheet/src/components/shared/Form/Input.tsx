import React, { useReducer } from "react";
import classes from "./Input.module.css";

type Props = {
  id: string;
  className?: string;
  label: string;
  element?: boolean;
  type: string;
  placeholder: string;
  initialInputValue: string;
  initialInputValidity: boolean;
};
type State = {
  value: string;
  isTouched: boolean;
  isValid: boolean;
};
type Action = {
  type: "CHANGE" | "TOUCH";
  value: string;
};

const inputReducer = (state: State, actions: Action): State => {
  switch (actions.type) {
    case "CHANGE":
      return {
        ...state,
        value: actions.value,
      };
    case "TOUCH": {
      return {
        ...state,
        isTouched: true,
      };
    }
    default:
      return state;
  }
};

const initialStateValue = (props: Props): State => {
  return {
    value: props.initialInputValue || "",
    isTouched: false,
    isValid: props.initialInputValidity,
  };
};

const Input: React.FC<Props> = (props) => {
  const [formState, dispatch] = useReducer(
    inputReducer,
    initialStateValue(props)
  );

  const changeInputHandler = (e: React.ChangeEvent<HTMLInputElement>) => {
    dispatch({
      type: "CHANGE",
      value: e.target.value,
    });
  };
  const changeTextAreaHandler = (e: React.ChangeEvent<HTMLTextAreaElement>) => {
    dispatch({
      type: "CHANGE",
      value: e.target.value,
    });
  };
  const touchHandler = () => {
    dispatch({
      type: "TOUCH",
      value: formState.value,
    });
  };

  const renderElement = props.element ? (
    <input
      id={props.id}
      type={props.type}
      placeholder={props.placeholder}
      onBlur={touchHandler}
      onChange={changeInputHandler}
      value={formState.value}
    />
  ) : (
    <textarea
      id={props.id}
      rows={3}
      onChange={changeTextAreaHandler}
      onBlur={touchHandler}
      value={formState.value}
    />
  );

  return (
    <div className={`${classes.formControl} ${props.className}`}>
      <label>{props.label}</label>
      {renderElement}
    </div>
  );
};

export default Input;
