import React from "react";
import classes from "./Card.module.css";

const Card: React.FC<{
  children: React.ReactNode;
  className?: String;
}> = ({ children, className }) => {
  return <div className={`${classes.card} ${className}`}>{children}</div>;
};
export default Card;
