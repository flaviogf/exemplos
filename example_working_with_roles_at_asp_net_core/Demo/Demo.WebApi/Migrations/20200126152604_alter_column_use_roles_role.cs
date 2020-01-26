using System;
using Microsoft.EntityFrameworkCore.Migrations;

namespace Demo.WebApi.Migrations
{
    public partial class alter_column_use_roles_role : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DeleteData(
                table: "Users",
                keyColumn: "Id",
                keyValue: new Guid("b41cea9d-476d-45a5-9fe1-9064ad60ec87"));

            migrationBuilder.DropColumn(
                name: "Roles",
                table: "Users");

            migrationBuilder.AddColumn<string>(
                name: "Role",
                table: "Users",
                nullable: false,
                defaultValue: "");

            migrationBuilder.InsertData(
                table: "Users",
                columns: new[] { "Id", "PasswordHash", "Role", "Username" },
                values: new object[] { new Guid("55fca4dc-7b1b-47b4-b798-9142fffc0a31"), "$2a$11$3UoNmHJrGqiyfZW6xtsYDezra9m0D9pYu6ahAE/JQ54EoB80JDfgi", "staff", "admin" });
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DeleteData(
                table: "Users",
                keyColumn: "Id",
                keyValue: new Guid("55fca4dc-7b1b-47b4-b798-9142fffc0a31"));

            migrationBuilder.DropColumn(
                name: "Role",
                table: "Users");

            migrationBuilder.AddColumn<string>(
                name: "Roles",
                table: "Users",
                type: "nvarchar(max)",
                nullable: false,
                defaultValue: "");

            migrationBuilder.InsertData(
                table: "Users",
                columns: new[] { "Id", "PasswordHash", "Roles", "Username" },
                values: new object[] { new Guid("b41cea9d-476d-45a5-9fe1-9064ad60ec87"), "$2a$11$xVBDYH6Y188UmgxbQSfEj.I0MnaRmPuXT3Y2kB/DV8dAxmiwYf/yi", "user,admin", "admin" });
        }
    }
}
