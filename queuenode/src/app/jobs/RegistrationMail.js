import Mail from "../../lib/Mail";

class RegistrationMail {
  get key() {
    return "RegistrationMail";
  }

  async handle({ data }) {
    const { user } = data;

    await Mail.sendMail({
      from: "QueueNode <queuenode@test.com.br>",
      to: user.email,
      subject: "Registration Mail",
      html: `Welcome, ${user.name}`
    });
  }
}

export default new RegistrationMail();
