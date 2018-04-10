package com.jdroid.component.builder.tasks

public class ReleaseJdroidComponentTask extends AbstractGitHubTask {

	@Override
	protected void onExecute() throws IOException {

		// Checking out

		File projectDir = getProjectDirectory()
		if (!projectDir.exists()) {
			execute('git clone ' + getRepositoryCloneUrl() + " " + getRepositoryName(), projectDir.getParentFile())
		}

		String ciGithubUserName = getGiHubUsername()
		if (ciGithubUserName != null) {
			execute('git config user.name '+ ciGithubUserName, projectDir)
		}
		String ciGithubUserEmail = getGiHubEmail()
		if (ciGithubUserEmail != null) {
			execute('git config user.email ' + ciGithubUserEmail, projectDir)
		}

		// TODO Signed commits should be forced
		execute('git config commit.gpgsign false', projectDir)

		// Synch production branch

		execute('git add -A', projectDir)
		execute('git stash', projectDir)
		execute('git checkout production', projectDir)
		execute('git pull', projectDir)

		execute('./gradlew clean :verifyJdroidTools :closeJdroidGitHubMilestone :createJdroidGitHubRelease ' +
				':generateJdroidChangelog uploadArchives --refresh-dependencies --stacktrace -PSNAPSHOT=true ' +
				'-PLOCAL_UPLOAD=false -PRELEASE_BUILD_TYPE_ENABLED=true -PRELEASE_FAKE_ENABLED=true -PACCEPT_SNAPSHOT_DEPENDENCIES=true', projectDir)
	}
}
