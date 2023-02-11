import { MongoClient } from "mongodb";

export const connectToDatabase = async () => {
  const client = await MongoClient.connect("mongodb://0.0.0.0:27017/nextapi");
  return client;
};
