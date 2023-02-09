import React from "react";
import Input from "./shared/Form/Input";
import Card from "./shared/UI/Card";
import classes from "./Main.module.css";

const Main: React.FC<{}> = (props) => {
  return (
    <div className={classes.main}>
      <Card>
        <h2>Post</h2>
        <div>
          <Input
            id="title"
            type="text"
            placeholder="Write a text to your post"
            initialInputValue=""
            label="Title"
            initialInputValidity={false}
          />
        </div>
      </Card>
    </div>
  );
};

export default Main;
